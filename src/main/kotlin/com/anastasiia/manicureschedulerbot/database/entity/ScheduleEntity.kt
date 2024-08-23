package com.anastasiia.manicureschedulerbot.database.entity

import com.anastasiia.manicureschedulerbot.database.vo.ScheduleTypeVo
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.time.DayOfWeek
import java.time.LocalTime
import java.util.EnumSet

@Entity
@Table(name = "schedules")
class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null

    @ElementCollection(targetClass = DayOfWeek::class)
    @CollectionTable(name = "schedule_weekends", joinColumns = [JoinColumn(name = "schedule_id")])
    @Column(name = "weekend_day")
    @Enumerated(EnumType.STRING)
    var restDaysOfWeek: Set<DayOfWeek> = EnumSet.noneOf(DayOfWeek::class.java)

    @Enumerated(EnumType.STRING)
    @Column(name = "schedule_types")
    var scheduleTypeVo: ScheduleTypeVo

    @Column(name = "start_working_time", nullable = false)
    var startWorkingTime: LocalTime

    @Column(name = "end_working_time", nullable = false)
    var endWorkingTime: LocalTime

    @ManyToMany
    @JoinTable(
        name = "schedule_manicurist",
        joinColumns = [JoinColumn(name = "schedule_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "manicurist_id", referencedColumnName = "id")],
    )
    var manicurists: List<ManicuristEntity>? = emptyList()

    constructor(
        restDaysOfWeek: Set<DayOfWeek>,
        scheduleTypeVo: ScheduleTypeVo,
        startWorkingTime: LocalTime,
        endWorkingTime: LocalTime,
    ) {
        this.restDaysOfWeek = restDaysOfWeek
        this.scheduleTypeVo = scheduleTypeVo
        this.startWorkingTime = startWorkingTime
        this.endWorkingTime = endWorkingTime
    }
}

/*


### 2. **Использование перечисления (`enum`):**

Более строгий подход — создание типа `enum` в PostgreSQL для хранения дней недели. Это обеспечит валидность данных, так как можно будет хранить только определенные значения.

#### Создание `enum` типа в PostgreSQL:
```sql
CREATE TYPE day_of_week AS ENUM ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY');

CREATE TABLE schedule (
    id SERIAL PRIMARY KEY,
    master_name VARCHAR(255),
    rest_days_of_week day_of_week[]
);
```

#### Пример использования в Spring Data JPA:
```java
import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.*;
import java.util.List;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String masterName;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "schedule_rest_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "rest_day")
    private List<DayOfWeek> restDaysOfWeek;

    // Getters and setters
}

public enum DayOfWeek {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
```

#### Пример записи данных:
```java
Schedule schedule = new Schedule();
schedule.setMasterName("Иван Иванов");
schedule.setRestDaysOfWeek(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY));

scheduleRepository.save(schedule);
```

### Выбор оптимального варианта:

- **Массив (`text[]`)**: Этот подход более гибкий и удобен, если тебе не нужно строгое ограничение на список возможных дней недели.
- **Перечисление (`enum`)**: Если ты хочешь обеспечить строгую валидность данных и исключить возможность неправильного ввода, то лучше использовать `enum`.

Оба подхода поддерживаются Spring Data JPA, и выбор зависит от конкретных требований проекта.


*/
