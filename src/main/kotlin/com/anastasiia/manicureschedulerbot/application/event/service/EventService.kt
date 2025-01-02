package com.anastasiia.manicureschedulerbot.application.event.service

import com.anastasiia.manicureschedulerbot.domain.event.model.Event
import com.anastasiia.manicureschedulerbot.domain.event.repository.IEventRepository
import com.anastasiia.manicureschedulerbot.domain.manicurist.valueobject.ManicuristId
import com.anastasiia.manicureschedulerbot.infrastructure.database.event.entity.EventEntity
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import java.time.LocalTime

@Service
class EventService(
    val eventRepository: IEventRepository,
) {
    fun saveEvent(event: EventEntity): EventEntity {
        return eventRepository.save(event)
    }

    // Данные рабочего графика
    val workingHours = listOf(
        LocalTime.of(9, 0),
        LocalTime.of(9, 30),
        LocalTime.of(10, 0),
        LocalTime.of(10, 30),
        LocalTime.of(11, 0),
        LocalTime.of(11, 30),
        LocalTime.of(12, 0),
        LocalTime.of(12, 30),
        LocalTime.of(14, 30),
        LocalTime.of(15, 0),
        LocalTime.of(15, 30),
        LocalTime.of(16, 0),
        LocalTime.of(16, 30),
    )

    // Записи на процедуры

//    fun getAvailableTimeSlots(): List<LocalTime> {
//        // Фильтруем доступные интервалы времени
//        return workingHours.filter { time ->
//            bookings.none { booking ->
//                time.isAfter(booking.first.minusMinutes(1)) &&
//                        time.isBefore(booking.second)
//            }
//        }
//    }

    fun bookEvent(event: Event, manicuristId: ManicuristId): Boolean {
//        val schedule =
        val bookedEvents = eventRepository.getEventsForCurrentDayByManicuristId(manicuristId)
        val isTimeInSchedule = workingHours.contains(event.timeFrom.toLocalTime())
        val isOverlapsWithAnyBookedEvents = bookedEvents.any { bookedEvent ->
            event.timeFrom.isBefore(bookedEvent.timeTo) &&
                event.timeTo.isAfter(bookedEvent.timeFrom)
        }
        if (!(isTimeInSchedule && !isOverlapsWithAnyBookedEvents)) {
            return false
        }
        eventRepository.save(event.toEntity())
        return true
    }

    @PostConstruct
    fun main() {
//        // Демонстрация работы алгоритма
//        println("Доступное время: ${getAvailableTimes()}")
//        println("Попытка записи на 9:00: ${bookEvent(LocalTime.of(9, 0))}")
//        println("Доступное время: ${getAvailableTimes()}")
//        println("Попытка записи на 9:30: ${bookEvent(LocalTime.of(9, 30))}")
//        println("Доступное время: ${getAvailableTimes()}")
//        println("Попытка записи на 10:30: ${bookEvent(LocalTime.of(10, 30))}")
//        println("Доступное время: ${getAvailableTimes()}")
    }
}
