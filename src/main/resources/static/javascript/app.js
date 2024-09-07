let telegram = window.Telegram.WebApp;
telegram.expand();

document.addEventListener("DOMContentLoaded", function () {
    const submitButton = document.getElementById("submit-time-picker-button")

    function submitTimePickerForm() {
        window.alert("POST REQUEST")
        const date = document.getElementById('date').value;
        const time = document.getElementById('time').value;
        const dateTime = new Date(`${date}T${time}`).toISOString();

        // userId: telegram.initData.user.id,

       // const reqBody = {
        //    telegramId: 123123,
        //    dateTime: new Date(Date.UTC(2024, 12, 3, 13, 12))
        // };
        console.log("Before request")

        fetch(callbackUrl, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                telegramId: telegram.initData.user.id,
                dateTime: dateTime
            })
        })
            .then(res => {
                console.log("Response status: ", res.status);
            })
            .catch(error => console.log('ERROR: ', error));
    }

    submitButton.addEventListener('click', () => {
        submitTimePickerForm()
    });
});
