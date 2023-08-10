# Reproduction for closed Hibernate sessions

Minimal reproduction for session closed in asynchronous contexts.

This repository contains 2 branches
- main - broken (Micronaut 4.0.3)
- micronaut-3 - working (Micronaut 3.10.x)

## Reproduce

`./gradlew run`

The log will show a stacktrace, while none should be expected

```
14:22:16.892 [scheduled-executor-thread-1] ERROR i.micronaut.scheduling.TaskExecutors - Error occurred executing @Async method [void listen(Event event)]: Session/EntityManager is closed
java.lang.IllegalStateException: Session/EntityManager is closed
	at org.hibernate.internal.AbstractSharedSessionContract.checkOpen(AbstractSharedSessionContract.java:429)
	at org.hibernate.engine.spi.SharedSessionContractImplementor.checkOpen(SharedSessionContractImplementor.java:186)
	at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:781)
	at org.hibernate.internal.AbstractSharedSessionContract.createQuery(AbstractSharedSessionContract.java:120)
	at io.micronaut.data.hibernate.operations.HibernateJpaOperations.createQuery(HibernateJpaOperations.java:175)
	at io.micronaut.data.hibernate.operations.HibernateJpaOperations.createQuery(HibernateJpaOperations.java:85)
	at io.micronaut.data.hibernate.operations.AbstractHibernateOperations.collectResults(AbstractHibernateOperations.java:363)
	at io.micronaut.data.hibernate.operations.AbstractHibernateOperations.collectFindOne(AbstractHibernateOperations.java:287)
	at io.micronaut.data.hibernate.operations.HibernateJpaOperations.lambda$findOne$3(HibernateJpaOperations.java:229)
	at io.micronaut.data.hibernate.operations.HibernateJpaOperations.lambda$executeRead$17(HibernateJpaOperations.java:454)
	at io.micronaut.transaction.support.AbstractPropagatedStatusTransactionOperations.lambda$execute$2(AbstractPropagatedStatusTransactionOperations.java:68)
	at io.micronaut.transaction.TransactionCallback.apply(TransactionCallback.java:37)
	at io.micronaut.transaction.support.AbstractTransactionOperations.executeTransactional(AbstractTransactionOperations.java:317)
	at io.micronaut.transaction.support.AbstractTransactionOperations.lambda$executeWithExistingTransaction$6(AbstractTransactionOperations.java:286)
	at io.micronaut.data.connection.support.AbstractConnectionOperations.withExistingConnectionInternal(AbstractConnectionOperations.java:128)
	at io.micronaut.data.connection.support.AbstractConnectionOperations.execute(AbstractConnectionOperations.java:92)
	at io.micronaut.transaction.support.AbstractTransactionOperations.executeWithExistingTransaction(AbstractTransactionOperations.java:285)
	at io.micronaut.transaction.support.AbstractTransactionOperations.executeWithExistingTransaction(AbstractTransactionOperations.java:218)
	at io.micronaut.transaction.support.AbstractTransactionOperations.doExecute(AbstractTransactionOperations.java:130)
	at io.micronaut.transaction.support.AbstractTransactionOperations.doExecute(AbstractTransactionOperations.java:124)
	at io.micronaut.transaction.support.AbstractPropagatedStatusTransactionOperations.execute(AbstractPropagatedStatusTransactionOperations.java:65)
	at io.micronaut.transaction.TransactionOperations.executeRead(TransactionOperations.java:76)
	at io.micronaut.data.hibernate.operations.HibernateJpaOperations.executeRead(HibernateJpaOperations.java:454)
	at io.micronaut.data.hibernate.operations.HibernateJpaOperations.findOne(HibernateJpaOperations.java:224)
	at io.micronaut.data.runtime.intercept.DefaultFindOptionalInterceptor.intercept(DefaultFindOptionalInterceptor.java:47)
	at io.micronaut.data.runtime.intercept.DefaultFindOptionalInterceptor.intercept(DefaultFindOptionalInterceptor.java:34)
	at io.micronaut.data.runtime.intercept.DataIntroductionAdvice.intercept(DataIntroductionAdvice.java:83)
	at io.micronaut.aop.chain.MethodInterceptorChain.proceed(MethodInterceptorChain.java:137)
	at com.example.EntityRepository$Intercepted.findById(Unknown Source)
	at com.example.Listener.listen(Listener.java:24)
	at com.example.$Listener$Definition$Intercepted.$$access$$listen(Unknown Source)
	at com.example.$Listener$Definition$Exec.dispatch(Unknown Source)
	at io.micronaut.context.AbstractExecutableMethodsDefinition$DispatchedExecutableMethod.invoke(AbstractExecutableMethodsDefinition.java:442)
	at io.micronaut.aop.chain.MethodInterceptorChain.proceed(MethodInterceptorChain.java:128)
	at io.micronaut.scheduling.async.AsyncInterceptor.lambda$intercept$3(AsyncInterceptor.java:111)
	at io.micronaut.core.propagation.PropagatedContext.lambda$wrap$3(PropagatedContext.java:211)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	at java.base/java.lang.Thread.run(Thread.java:833)
```