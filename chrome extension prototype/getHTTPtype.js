function getCurrentTabUrl(callback) {
    console.log("inside getCurrentTabUrl");
    chrome.tabs.query(queryInfo, function(tabs){
        console.log("inside tabs");
        var tab = tabs[0];
        var url = tab.url;
        callback(url);
        var arr = url.split("/");
        var httpType = arr[0];
        var logger = chrome.extension.getBackgroundPage();
        console.log("http type is", httpType);
    })
}
