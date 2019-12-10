package com.sun.customer.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;
import javax.transaction.UserTransaction;

/**
 * @ClassName: ReportConfigure
 * @Description: (读取配置文件)
 * @author
 * @date
 */
@Configuration
public class ReportConfigure {


	@Bean(name = "xatx")
	public JtaTransactionManager regTransactionManager () {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		UserTransaction userTransaction = new UserTransactionImp();
		return new JtaTransactionManager(userTransaction, userTransactionManager);
	}

	@Bean(name = "userTransaction")
	public UserTransaction userTransaction() throws Throwable {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(10000);
		return userTransactionImp;
	}

}
