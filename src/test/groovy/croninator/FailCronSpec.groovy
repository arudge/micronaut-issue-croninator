package croninator

import io.micronaut.context.ApplicationContext
import io.micronaut.scheduling.annotation.Scheduled
import spock.lang.Specification

import javax.inject.Singleton

class FailCronSpec extends Specification {

  void 'job fails with exception'() {
    given:
    ApplicationContext beanContext = ApplicationContext.run()

    when:
    MyTask myTask = beanContext.getBean(MyTask)

    then:
    !myTask.wasRun //never runs
  }

  @Singleton
  static class MyTask {
    boolean wasRun = false

    @Scheduled(cron = '* * * * *')
    void runCron() {
      wasRun = true
    }
  }
}


