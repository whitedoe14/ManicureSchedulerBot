const MONTHS: string[] = [
    "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
];
const DAYS_IN_WEEK: number = 7;

let currentDate: Date = new Date();
let currentYear: number = currentDate.getFullYear();
let currentMonth: number = currentDate.getMonth();

const calendarDates: HTMLElement | null = document.getElementById("calendar-dates");
const calendarCurrentDate: HTMLElement | null = document.getElementById("calendar-current-date");
const prevIcon: HTMLElement | null = document.getElementById("calendar-prev");
const nextIcon: HTMLElement | null = document.getElementById("calendar-next");

function getFirstDayOfMonth(year: number, month: number): number {
    return new Date(year, month, 1).getDay() || DAYS_IN_WEEK;
}

function getLastDateOfMonth(year: number, month: number): number {
    return new Date(year, month + 1, 0).getDate();
}

function generatePreviousMonthDays(year: number, month: number): string {
    const firstDay: number = getFirstDayOfMonth(year, month);
    const lastDateOfLastMonth: number = new Date(year, month, 0).getDate();
    let calendar: string = "";

    for (let i: number = firstDay; i > 1; i--) {
        calendar += `<li class="inactive">${lastDateOfLastMonth - i + 2}</li>`;
    }
    return calendar;
}

function generateCurrentMonthDays(year: number, month: number): string {
    const lastDate: number = getLastDateOfMonth(year, month);
    let calendar: string = "";

    for (let i: number = 1; i <= lastDate; i++) {
        const isToday: boolean = i === currentDate.getDate() &&
            month === new Date().getMonth() &&
            year === new Date().getFullYear();
        const dateStatus: string = isToday ? "active" : "";
        calendar += `<li class="${dateStatus}">${i}</li>`;
    }
    return calendar;
}

function generateNextMonthDays(year: number, month: number): string {
    const lastDayOfMonth: number = new Date(year, month, getLastDateOfMonth(year, month)).getDay();
    const daysToAdd: number = lastDayOfMonth === 0 ? 0 : DAYS_IN_WEEK - lastDayOfMonth;
    let calendar: string = "";

    for (let i: number = 1; i <= daysToAdd; i++) {
        calendar += `<li class="inactive">${i}</li>`;
    }
    return calendar;
}

function updateCalendar(): void {
    let calendar: string = generatePreviousMonthDays(currentYear, currentMonth);
    calendar += generateCurrentMonthDays(currentYear, currentMonth);
    calendar += generateNextMonthDays(currentYear, currentMonth);

    if (calendarCurrentDate) {
        calendarCurrentDate.innerText = `${MONTHS[currentMonth]} ${currentYear}`;
    }
    if (calendarDates) {
        calendarDates.innerHTML = calendar;
    }
}

function changeMonth(increment: number): void {
    currentMonth += increment;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    } else if (currentMonth > 11) {
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
