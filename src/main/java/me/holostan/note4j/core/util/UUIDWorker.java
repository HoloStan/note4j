package me.holostan.note4j.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by ghu on 7/19/2017.
 */
public class UUIDWorker {

    private static Logger logger = LoggerFactory.getLogger(UUIDWorker.class);

    public synchronized static String next() {
        String uuid = UUID.randomUUID().toString();
        logger.info("Gen uuid: " + uuid);
        return uuid;
    }

}
