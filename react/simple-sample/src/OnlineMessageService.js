
class OnlineMessageService{
    getRosterList(userId, callback){
        console.log(userId);
        var rosterList = [
            {title:"title1", description:"description1", id:"user1"},
            {title:"title2", description:"description2", id:"user2"},
            {title:"title3", description:"description3", id:"user3"},
        ];
        callback(rosterList);
    }
}

export default OnlineMessageService;