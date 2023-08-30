package com.melis.todoProject.locking;

import com.google.common.util.concurrent.Striped;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

@Component
public class LockTemplate {
    private final Striped<Lock> lockStriped = Striped.lazyWeakLock(2);

    public <T> void setLock(T key, Runnable code) {
        Lock lock = lockStriped.get(key);
        lock.lock();
        code.run();
        lock.unlock();
    }
}
