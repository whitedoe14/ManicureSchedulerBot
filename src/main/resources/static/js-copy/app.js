const webApp = window.Telegram.WebApp;
webApp.expand();

function submitTimePickerForm() {
    const date = document.getElementById("date").value;
    const time = document.getElementById("time").value;

    const telegramUserId = webApp.initDataUnsafe.user.id;
    const dateTime = new Date(`${date}T${time}`).toISOString();

    console.log("Before request")
    // https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication#authentication_schemes
    // https://core.telegram.org/bots/webapps#validating-data-received-via-the-mini-app
    /*
        query_id=AAG07TIsAAAAALTtMiwU0k4R&
        user=%7B%22id%22%3A741535156%2C%22first_name%22%3A%22Vitalik%20%F0%9F%AA%B6%22%2C%22last_name%22%3A%22%22%2C%22username%22%3A%22pirog_y%22%2C%22language_code%22%3A%22en%22%2C%22allows_write_to_pm%22%3Atrue%7D&
        auth_date=1725825289&
        hash=0de95201e5fc28616a35e9ccb577daf9465198cca91d3b1afec9c8d9c9e796c3

     */
    fetch(callbackUrl.concat("/submit-book"), {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + webApp.initData,
        },
        body: JSON.stringify({
            telegramUserId: telegramUserId,
            dateTime: dateTime
        })
    })
        .then(res => {
            console.log("Response status: ", res.status);
        })
        .catch(error => console.log("ERROR: ", error));
}
