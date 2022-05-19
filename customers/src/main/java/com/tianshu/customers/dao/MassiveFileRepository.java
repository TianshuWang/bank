package com.tianshu.customers.dao;

import com.tianshu.customers.entity.MassiveFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MassiveFileRepository extends JpaRepository<MassiveFile,Long> {

    MassiveFile findByFileName(String fileName);
}
