package com.mkalaimalai.common.domain.event.publisher;

import com.mkalaimalai.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
