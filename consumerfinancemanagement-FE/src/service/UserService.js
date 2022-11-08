import axios from 'axios';

const USER_URL = "http://localhost:8080/consumerfinancemanagement/api/";

class UserService {

    getUsers() {
        return axios.get(USER_URL + "find/rob1234");
    }

    postUsers(req) {
        
        return axios.post(USER_URL + "user", JSON.parse(req));
    }
}

export default new UserService();