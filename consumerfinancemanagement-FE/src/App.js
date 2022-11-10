import React from 'react';
// import './App.css';
import Navbar from './components/Navbar';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/UserLogin';
import Form from './pages/RegisterUser';
import UserList from './components/Navbar/UserList';
import Products from './pages/productlist';
import Adminlogin from './pages/AdminLogin'
import ForgotPassword from './pages/ForgotPassword';
import CardDashboard from './pages/CardDashboard';
import Productinfo from './pages/productinfo';
import AdminDashboard from './pages/AdminDashboard';

function App() {
return (
  
	<Router>
	{/* <Navbar /> */}
  	{/* <UserList /> */}
	<Routes>
		<Route index path='/' exact element={<Home />} />
		{/* <Route path='/index' exact element={<Home />} /> */}
		<Route path='/login' element={<Login />} />
		<Route path='/sign-up' element={<Form />} />
		<Route path='/users' element={<UserList />} />
		<Route path='/login/admin' element={<Adminlogin />} />
		<Route path='/admindashboard' element={<AdminDashboard />} />
		<Route path='/login/forgotpassword' element={<ForgotPassword />} />
		<Route path='/users/productlist' element={<Products />} />
		<Route path='/users/productlist/productinfo' element={<Productinfo />} />
		<Route path='/users/cardDashboard' element={<CardDashboard />} />
	</Routes>
	</Router>
);
}

export default App;
