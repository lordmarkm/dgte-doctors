package com.dgtedr.backend.doctor.service;

import com.dgtedr.backend.doctor.model.Specialty;
import com.dgtedr.backend.doctor.service.custom.SpecialtyServiceCustom;
import com.efs.core.jpa.service.ExistJpaService;

/**
 *
 * @author mbmartinez on 11 Jun 2018
 *
 */
public interface SpecialtyService extends ExistJpaService<Specialty>, SpecialtyServiceCustom {

}
