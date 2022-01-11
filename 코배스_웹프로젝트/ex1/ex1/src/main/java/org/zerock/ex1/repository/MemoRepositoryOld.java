package org.zerock.ex1.repository;

import org.springframework.stereotype.Repository;
import org.zerock.ex1.entity.Memo;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemoRepositoryOld {

    @PersistenceContext
    EntityManager em;

    public Long save(Memo memo) {
        em.persist(memo);
        return memo.getId();
    }

    public Memo findById(Long id) {
        Memo findMemo = em.find(Memo.class, id);
        return findMemo;
    }

    public void deleteById(Long id) {
        Memo deleteMemo = em.find(Memo.class, id);
        em.remove(deleteMemo);
    }
}
