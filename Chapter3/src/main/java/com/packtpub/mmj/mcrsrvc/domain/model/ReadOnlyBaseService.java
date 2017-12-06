package com.packtpub.mmj.mcrsrvc.domain.model;

/**
 *
 * @author Sourabh Sharma
 * @param <TE>
 * @param <T>
 */
public abstract class ReadOnlyBaseService<TE, T> {

    private final ReadOnlyRepository<TE, T> repository;

    ReadOnlyBaseService(ReadOnlyRepository<TE, T> repository) {
        this.repository = repository;
    }
}
