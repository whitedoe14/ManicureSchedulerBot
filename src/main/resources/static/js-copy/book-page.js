const tgWebApp = window.Telegram.WebApp
tgWebApp.expand()

// Set theme
document.documentElement.style.setProperty("--tg-theme-bg-color", tgWebApp.backgroundColor)
document.documentElement.style.setProperty("--tg-theme-text-color", tgWebApp.textColor)
document.documentElement.style.setProperty("--tg-theme-hint-color", tgWebApp.hintColor)
document.documentElement.style.setProperty("--tg-theme-link-color", tgWebApp.linkColor)
document.documentElement.style.setProperty("--tg-theme-button-color", tgWebApp.buttonColor)
document.documentElement.style.setProperty("--tg-theme-button-text-color", tgWebApp.buttonTextColor)

// Calendar logic
let currentDate = new Date()
let selectedDate = null
let selectedTimeSlot = null

const monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"]

const daysGrid = document.getElementById("daysGrid")
const currentMonthElement = document.getElementById("currentMonth")
const prevMonthBtn = document.getElementById("prevMonth")
const nextMonthBtn = document.getElementById("nextMonth")
const timeSlotsContainer = document.getElementById("timeSlots")
const submitBtn = document.getElementById("submitBtn")

function updateCalendar() {
    const year = currentDate.getFullYear()
    const month = currentDate.getMonth()

    currentMonthElement.textContent = `${monthNames[month]} ${year}`

    const firstDay = new Date(year, month, 1)
    const lastDay = new Date(year, month + 1, 0)

    daysGrid.innerHTML = ""

    // Add empty cells for days before the first day of the month
    for (let i = 0; i < (firstDay.getDay() || 7) - 1; i++) {
        daysGrid.appendChild(document.createElement("div"))
    }

    // Add days of the month
    for (let day = 1; day <= lastDay.getDate(); day++) {
        const dayElement = document.createElement("div")
        dayElement.textContent = day

        const dayDate = new Date(year, month, day)
        if (dayDate < new Date().setHours(0,0,0,0)) {
            dayElement.classList.add("disabled")
        } else {
            dayElement.addEventListener("click", () => selectDate(dayDate))
        }

        if (selectedDate && dayDate.toDateString() === selectedDate.toDateString()) {
            dayElement.classList.add("selected")
        }

        daysGrid.appendChild(dayElement)
    }

    // Disable next button if we"re 3 months ahead
    const threeMonthsLater = new Date()
    threeMonthsLater.setMonth(threeMonthsLater.getMonth() + 3)
    nextMonthBtn.disabled = currentDate >= threeMonthsLater
}

function selectDate(date) {
    selectedDate = date
    updateCalendar()
    fetchTimeSlots(date)
}

async function fetchTimeSlots(date) {
    // Здесь должен быть реальный запрос к вашему бэкенду
    // Для примера используем фиктивные данные
    const response = await new Promise(resolve => setTimeout(() => resolve({
        ok: true,
        json: () => Promise.resolve(["10:00", "11:00", "14:00", "15:00", "16:00"])
    }), 500))

    if (response.ok) {
        const slots = await response.json()
        displayTimeSlots(slots)
    } else {
        console.error("Failed to fetch time slots")
        timeSlotsContainer.innerHTML = "<p>Failed to load time slots. Please try again.</p>"
    }
}

function displayTimeSlots(slots) {
    timeSlotsContainer.innerHTML = ""
    slots.forEach(slot => {
        const slotElement = document.createElement("div")
        slotElement.textContent = slot
        slotElement.classList.add("time-slot")
        slotElement.addEventListener("click", () => selectTimeSlot(slot, slotElement))
        timeSlotsContainer.appendChild(slotElement)
    })
}

function selectTimeSlot(slot, element) {
    if (selectedTimeSlot) {
        selectedTimeSlot.classList.remove("selected")
    }
    selectedTimeSlot = element
    selectedTimeSlot.classList.add("selected")
}

prevMonthBtn.addEventListener("click", function () {
    // currentDate.setMonth(currentDate.getMonth() - 1)
    // updateCalendar()
    alert("BOBO")
})

nextMonthBtn.addEventListener("click", () => {
    currentDate.setMonth(currentDate.getMonth() + 1)
    updateCalendar()
})

submitBtn.addEventListener("click", () => {
    if (selectedDate && selectedTimeSlot) {
        const result = {
            date: selectedDate.toISOString().split("T")[0],
            time: selectedTimeSlot.textContent
        }
        tgWebApp.sendData(JSON.stringify(result))
        tgWebApp.close()
    } else {
        alert("Please select both date and time")
    }
})

// Initial calendar update
updateCalendar()
