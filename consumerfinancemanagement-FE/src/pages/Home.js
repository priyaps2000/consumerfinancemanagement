import React,{Component} from "react";
import axios from 'axios';
import withNavigateHook from '../components/withNavigateHook';
import '../style/Home.css';
import Navbar from '../components/Navbar';
 
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
        <div>
            <Navbar />
            <div className="home-container">
                <p className="tagline">Financial Solutions for your Personal Needs</p>
                <div className="product-ctr">
                    <p className="trending">TRENDING</p>
                    <div className="trending-product">
                        <img src={this.state.productURL}></img>
                        <div className="details">
                            <p className="prod-title">{this.state.productName}</p>
                            <p>Product Details: {this.state.productDetails}</p>
                            <p>Cost: Rs. {this.state.cost} /-</p>
                            <button className="home-button" onClick={() => this.logout()}>Buy Now</button>
                        </div>
                    </div>
                    
                </div>
                <div className="terms">
                    <p>Terms and Conditions</p>
                    <p>All customers can avail services upon activation of EMI card. Standard charges are applicable for processing card applications. All products are eligible for return/exchange within 30 days.</p>
                    <p></p>
                </div>
            </div>
        </div>
        );
    }
}

export default withNavigateHook(Home);