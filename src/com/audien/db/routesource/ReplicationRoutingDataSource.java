package com.audien.db.routesource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplicationRoutingDataSource.class);


	@Override
	protected Object determineCurrentLookupKey() {
		String dataSourceType = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "read" : "write";
		logger.info("TransactionSynchronizationManager.isCurrentTransactionReadOnly() : {}", TransactionSynchronizationManager.isCurrentTransactionReadOnly());
		logger.info("current dataSourceType : {}", dataSourceType);
        return dataSourceType;
	}

}
