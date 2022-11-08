import React, { Component } from 'react';
import UserService from '../../service/UserService';

var a;

class UserList extends Component {
    constructor(props) {
        super(props)

        this.state = {
            users: []
        }
    }

    componentDidMount() {
        UserService.getUsers().then((res) => {
            this.setState({users: res.data});
            a = Object.keys(this.state.users);
            console.log(this.state.users);
            console.log(Object.keys(this.state.users));
            console.log(this.state.users["userName"]);
        });
        
    }
    
    render() {
        
        return (
            <div>{this.state.users["userName"]}</div>
        )
            
    }

}
export default UserList;