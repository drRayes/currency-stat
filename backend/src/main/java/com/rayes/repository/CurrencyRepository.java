package com.rayes.repository;

import com.rayes.entity.Currency;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    List<Optional<Currency>> findByDateOfValue(LocalDate localDate);
    long deleteByDateOfValue(LocalDate localDate);
}
