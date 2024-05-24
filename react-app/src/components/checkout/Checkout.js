import {useContext, useState, useEffect} from 'react';
import axios from 'axios';
import styles from '../../styles/Checkout.module.css';
import {AppContext} from "../../context/AppContext";
import { loadStripe } from '@stripe/stripe-js';
import { Elements } from '@stripe/react-stripe-js';
import CheckoutForm from "./CheckoutForm";
import { useNavigate } from 'react-router-dom';

const stripePromise = loadStripe('pk_test_51PJSo6P4V1jTXpjXNrCWoJQpW4riyAETnUpTekhSAvBTHdtGisNYdssHo2rZYcnFGvToZJxru1LtWlzWDJVo1dRF00EWu9Ppe7');

const Checkout = () => {
    const {cart} = useContext(AppContext);
    const [clientSecret, setClientSecret] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        if(cart.length === 0)
            navigate('/');
        else {
            const data = {
                amount: cart.reduce((price, product) => price + product.price, 0)
            };
            axios.post("http://localhost:8080/api/payments/create-payment-intent", JSON.stringify(data), {
                headers: {"Content-Type": "application/json"},
            })
            .then((res) => {
                setClientSecret(res.data);
            });
        }

    }, [cart, navigate]);

    const appearance = {
        theme: 'stripe',
    };
    const options = {
        clientSecret,
        appearance,
    };

    return (
        <>
            <h1>Make Payment</h1>
            <div className={styles.formContainer}>
                {clientSecret &&
                    <Elements stripe={stripePromise} options={options} >
                        <CheckoutForm clientSecret={clientSecret} />
                    </Elements>
                }
            </div>
        </>
    );
}

export default Checkout;