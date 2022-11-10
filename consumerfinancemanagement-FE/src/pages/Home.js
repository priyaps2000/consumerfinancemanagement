import React,{Component} from "react";
import axios from 'axios';
import withNavigateHook from '../components/withNavigateHook';
 
export class Home extends Component{
 
    constructor(props) {
        super(props)
 
        this.state ={
            productName: '',
            productURL: '',
            purchaseCount: '',
            cost: '',
            productDetails: ''
        }
    }

	logout(){
        this.props.navigation('/login');
    }
 
    componentDidMount() {
        axios.get("http://localhost:8080/consumerfinancemanagement/api/product/productMax").then((res) => {
            console.log(res["data"])
            this.setState({productName: res["data"]["productName"]})
            this.setState({productURL: res["data"]["productURL"]})
            this.setState({cost: res["data"]["cost"]})
            this.setState({productDetails: res["data"]["productDetails"]})
        })
    }
 
    render(){
        return(
            <div className="container">
                <div className="product">
                    <img src={this.state.productURL}></img>
                    <div className="details">
                        <p>{this.state.productName}</p>
                        <p>Product Details: {this.state.productDetails}</p>
                        <p>Cost: Rs. {this.state.productDetails} /-</p>
                		<button onClick={() => this.logout()}>Buy Now</button>
                    </div>
                </div>
                <div className="terms">
                    <p>Terms and Conditions</p>
                </div>
            </div>
        );
    }
}

export default withNavigateHook(Home);