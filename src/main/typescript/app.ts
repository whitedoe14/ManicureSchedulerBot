interface Window {
    appConfig: {
        callbackUrl: string;
    }
}

const webApp: any = Telegram.WebApp;
webApp.expand();

function submitTimePickerForm(): void {
    const date: string = (document.getElementById("date") as HTMLInputElement).value;
    const time: string = (document.getElementById("time") as HTMLInputElement).value;

    const telegramUserId: number = webApp.initDataUnsafe.user.id;
    const dateTime: string = new Date(`${date}T${time}`).toISOString();

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
        .then((res: Response) => {
            console.log("Response status: ", res.status);
        })
        .catch((error: Error) => console.log("ERROR: ", error));
}
