package com.ampota.card.batch;

import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.GsonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ampota.card.model.Card;
import com.ampota.card.service.CardService;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

@Configuration
@EnableBatchProcessing
public class CardReaderBatchConfig {

    private static final Logger LOG = LoggerFactory.getLogger(CardReaderBatchConfig.class);

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private CardService cardService;

    @PostConstruct
    public void run() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        if (repo.count() > 0) {
            return;
        }
        LOG.info("Parsing cards");
        JobParameters param = new JobParametersBuilder()
            .addString("JobId", String.valueOf(System.currentTimeMillis()))
            .addString("JobName", "read-cards").toJobParameters();
        jobLauncher.run(csvFileToDatabaseJob(), param);
    }

    @Bean
    public Job csvFileToDatabaseJob() {
        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .flow(csvFileToDatabaseStep())
                .end()
                .build();
    }

    @Bean
    public Step csvFileToDatabaseStep() {
        return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<LinkedTreeMap, Card>chunk(1000)
                .reader(reader())
                .processor(cardProcessor())
                .writer(cardWriter())
                .build();
    }

    @Bean
    public JsonItemReader<LinkedTreeMap> reader() {
        Gson gson = new Gson();
        GsonJsonObjectReader<LinkedTreeMap> jsonObjectReader = new GsonJsonObjectReader<>(LinkedTreeMap.class);
        jsonObjectReader.setMapper(gson);

        try {
            return new JsonItemReaderBuilder<LinkedTreeMap>()
                    .jsonObjectReader(jsonObjectReader)
                    .resource(new FileUrlResource(Paths.get("scryfall_bulk_json", "scryfall-all-cards.json").toString()))
                    .name("tradeJsonItemReader")
                    .build();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    ItemProcessor<LinkedTreeMap, Card> cardProcessor() {
        return new CardProcessor();
    }

    @Bean
    public ItemWriter<Card> cardWriter() {
        ItemWriter<Card> cardWriter = new ItemWriter<Card>() {
            @Override
            @Transactional(propagation = Propagation.REQUIRES_NEW)
            public void write(List<? extends Card> items) throws Exception {
                LOG.info("Writing cards. items={}", items.size());
                cardService.saveAll(items);
            }
        };
        return cardWriter;
    }

}
