package com.dgtedr.backend.doctor.service.custom.impl;

import com.dgtedr.backend.doctor.model.Specialty;
import com.dgtedr.backend.doctor.service.SpecialtyService;
import com.dgtedr.backend.doctor.service.custom.SpecialtyServiceCustom;
import com.dgtedr.backend.shared.dto.SpecialtyInfo;
import com.efs.core.jpa.service.ExistJpaServiceCustomImpl;

/**
 *
 * @author mbmartinez on 11 Jun 2018
 *
 */
public class SpecialtyServiceCustomImpl extends ExistJpaServiceCustomImpl<Specialty, SpecialtyInfo, SpecialtyService>
    implements SpecialtyServiceCustom {

}
