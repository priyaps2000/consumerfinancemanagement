import axios from 'axios';

const USER_URL = "http://localhost:8080/consumerfinancemanagement/api/";

class UserService {

    getUsers() {
        return axios.get(USER_URL + "find/rob1234");
    }

    postUsers(req) {
        
        return axios.post(USER_URL + "user/", JSON.parse(req));
    }

    purchaseProduct(user){
        return axios.post("http://localhost:8080/consumerfinancemanagement/api/sale/buyNow/" + user["username"] + "/" + user["productid"] + "/" + user["EMI"], {headers: {"Content-Type": "text/plain"}})
    }
}

export default new UserService();