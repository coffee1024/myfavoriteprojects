package com.coffee.photo.repository.file;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coffee.photo.entity.file.PhotoFile;

public interface PhotoFileDao extends PagingAndSortingRepository<PhotoFile, Long>, JpaSpecificationExecutor<PhotoFile> {

}
