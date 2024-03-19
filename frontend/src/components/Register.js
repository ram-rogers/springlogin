import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';





const Register = () => {

    const navigate = useNavigate();


    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: ''
    });


    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/save', formData);
            toast.success("Registration Success " + response.data);
            setTimeout(() => {
                navigate("/login");
            }, 2000);

        } catch (error) {
            toast.error("Registration Failed " + error)
        }
        finally {
            setFormData({
                username: '',
                email: '',
                password: ''
            });

        }
    };



    return (




        <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
            <ToastContainer position="top-center" autoClose={2000} />

            <div className="max-w-md w-full space-y-8">

                <div>
                    <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
                        Register your account
                    </h2>
                </div>
                <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
                    <input type="hidden" name="remember" value="true" />
                    <div className="rounded-md shadow-sm -space-y-px">
                        <div>
                            <label htmlFor="username" className="sr-only">Username</label>
                            <input id="username" name="username" type="text" autoComplete="username" required className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Username" value={formData.username} onChange={handleChange} />
                        </div>

                        <div>
                            <label htmlFor="email" className="sr-only">Email</label>
                            <input id="email" name="email" type="text" autoComplete="email" required className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Email" value={formData.email} onChange={handleChange} />
                        </div>


                        <div>
                            <label htmlFor="password" className="sr-only">Password</label>
                            <input id="password" name="password" type="password" autoComplete="current-password" required className="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-b-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm" placeholder="Password" value={formData.password} onChange={handleChange} />
                        </div>
                    </div>

                    <div>
                        <button type="submit" className="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                            Log in
                        </button>
                    </div>


                </form>
                <div className="text-sm text-center mt-4">
                    Already have an account? <a href="/login" className="font-medium text-indigo-600 hover:text-indigo-500">Log in here</a>
                </div>
            </div>
        </div>
    )
}

export default Register