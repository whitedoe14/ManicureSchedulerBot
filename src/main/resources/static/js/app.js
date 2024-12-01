"use strict";
const webApp = Telegram.WebApp;
webApp.expand();
function submitTimePickerForm() {
    const date = document.getElementById("date").value;
    const time = document.getElementById("time").value;
    const telegramUserId = webApp.initDataUnsafe.user.id;
    const dateTime = new Date(`${date}T${time}`).toISOString();
    console.log("Before request");
    fetch(window.appConfig.callbackUrl.concat("/submit-book"), {
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
        .then((res) => {
        console.log("Response status: ", res.status);
    })
        .catch((error) => console.log("ERROR: ", error));
}
//# sourceMappingURL=app.js.map