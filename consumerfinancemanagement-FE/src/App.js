import React from 'react';
import './App.css';
import Navbar from './components/Navbar';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/UserLogin';
import Form from './pages/RegisterUser';
import UserList from './components/Navbar/UserList';
import Products from './pages/productlist';
import Adminlogin from './pages/AdminLogin'
import ForgotPassword from './pages/ForgotPassword';

function App() {
return (
  
	<Router>
	<Navbar />
  	{/* <UserList /> */}
	<Routes>
		<Route path='/' exact element={<Home />} />
		<Route path='/index' exact element={<Home />} />
		<Route path='/login' element={<Login />} />
		<Route path='/sign-up' element={<Form />} />
		<Route path='/users' element={<UserList />} />
		<Route path='/login/adminlogin' element={<Adminlogin />} />
		<Route path='/login/forgotpassword' element={<ForgotPassword />} />
		<Route path='/users/productlist' element={<Products />} />
	</Routes>
	</Router>
);
}

export default App;
