package com.pouchen.scmocp.scmocpapi.dao.pet;

import com.pouchen.scmocp.scmocpapi.entity.Pet;
import org.springframework.data.repository.CrudRepository;

// 第一個參數為訪問的實體，第二參數是這個Entity ID的資料型態
public interface PetDao extends CrudRepository<Pet, Integer> {

}