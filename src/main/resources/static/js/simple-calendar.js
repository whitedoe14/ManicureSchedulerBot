"use strict";
const MONTHS = [
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];
const DAYS_IN_WEEK = 7;
let currentDate = new Date();
let currentYear = currentDate.getFullYear();
let currentMonth = currentDate.getMonth();
const calendarDates = document.getElementById("calendar-dates");
const calendarCurrentDate = document.getElementById("calendar-current-date");
const prevIcon = document.getElementById("calendar-prev");
const nextIcon = document.getElementById("calendar-next");
function getFirstDayOfMonth(year, month) {
    return new Date(year, month, 1).getDay() || DAYS_IN_WEEK;
}
function getLastDateOfMonth(year, month) {
    return new Date(year, month + 1, 0).getDate();
}
function generatePreviousMonthDays(year, month) {
    const firstDay = getFirstDayOfMonth(year, month);
    const lastDateOfLastMonth = new Date(year, month, 0).getDate();
    let calendar = "";
    for (let i = firstDay; i > 1; i--) {
        calendar += `<li class="inactive">${lastDateOfLastMonth - i + 2}</li>`;
    }
    return calendar;
}
function generateCurrentMonthDays(year, month) {
    const lastDate = getLastDateOfMonth(year, month);
    let calendar = "";
    for (let i = 1; i <= lastDate; i++) {
        const isToday = i === currentDate.getDate() &&
            month === new Date().getMonth() &&
            year === new Date().getFullYear();
        const dateStatus = isToday ? "active" : "";
        calendar += `<li class="${dateStatus}">${i}</li>`;
    }
    return calendar;
}
function generateNextMonthDays(year, month) {
    const lastDayOfMonth = new Date(year, month, getLastDateOfMonth(year, month)).getDay();
    const daysToAdd = lastDayOfMonth === 0 ? 0 : DAYS_IN_WEEK - lastDayOfMonth;
    let calendar = "";
    for (let i = 1; i <= daysToAdd; i++) {
        calendar += `<li class="inactive">${i}</li>`;
    }
    return calendar;
}
function updateCalendar() {
    let calendar = generatePreviousMonthDays(currentYear, currentMonth);
    calendar += generateCurrentMonthDays(currentYear, currentMonth);
    calendar += generateNextMonthDays(currentYear, currentMonth);
    if (calendarCurrentDate) {
        calendarCurrentDate.innerText = `${MONTHS[currentMonth]} ${currentYear}`;
    }
    if (calendarDates) {
        calendarDates.innerHTML = calendar;
    }
}
function changeMonth(increment) {
    currentMonth += increment;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    else if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    updateCalendar();
}
updateCalendar();
if (prevIcon) {
    prevIcon.addEventListener("click", () => changeMonth(-1));
}
if (nextIcon) {
    nextIcon.addEventListener("click", () => changeMonth(1));
}
//# sourceMappingURL=simple-calendar.js.map