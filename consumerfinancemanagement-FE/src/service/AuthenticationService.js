import axios from 'axios'; // For API calls, we will be using Axios - npm install axios

const API_URL='http://localhost:8080/consumerfinancemanagement/api/';
const API_URL1='http://localhost:8085/ims/api/dealers';

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser';

class AuthenticationService{

    loginUser(user){
        return axios.post(API_URL+"checkUser/"+user["email"], user["password"], {headers: {"Content-Type": "text/plain"}});
    }

    registerDealer(dealer){
        return axios.post(API_URL1, dealer);
       }

    registerSuccessfulLogin(username, password) {
      
       sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
      // console.log("First"+username);
      
    }

    logout() {
     
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return false
        return true
    }

    getLoggedInUserName() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
       // console.log("Second"+user);
        if (user === null) return ''
        return user
    }

} 
//Create an object of AuthenticationService.
export default new AuthenticationService();