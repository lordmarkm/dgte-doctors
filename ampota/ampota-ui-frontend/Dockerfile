FROM nginx:latest
COPY ./dist /usr/share/nginx/html
ENV TZ=Asia/Singapore
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
