package com.jfms.authentication.authentication.dal.repository;

import com.jfms.authentication.authentication.dal.entity.ActivationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ActivationRepository extends CrudRepository<ActivationEntity, Long> {
//    @Query("select a from ActivationEntity a where a.activationCode = :activationCode and a.used = :isUsed")
    ActivationEntity findActivationEntityByActivationCodeAndIsUsedAndMobileNumber(
            String activationCode,
            Boolean isUsed,
            String mobileNumber
    );
    ActivationEntity findTopByMobileNumberOrdOrderByCreationTimeDesc(String mobileNumber);
}
