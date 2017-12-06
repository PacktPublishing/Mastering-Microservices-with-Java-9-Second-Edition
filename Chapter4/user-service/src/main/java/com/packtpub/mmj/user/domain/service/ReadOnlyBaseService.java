package com.packtpub.mmj.user.domain.service;

import com.packtpub.mmj.user.domain.repository.ReadOnlyRepository;

/**
 *
 * @author Sourabh Sharma
 * @param <TE>
 * @param <T>
 */
public abstract class ReadOnlyBaseService<TE, T> {

    private ReadOnlyRepository<TE, T> repository;

    ReadOnlyBaseService(ReadOnlyRepository<TE, T> repository) {
        this.repository = repository;
    }
}
