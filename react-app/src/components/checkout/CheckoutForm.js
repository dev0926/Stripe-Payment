import styles from "../../styles/Checkout.module.css";
import {useContext, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import {AppContext} from "../../context/AppContext";
import { useStripe, useElements, PaymentElement, AddressElement } from '@stripe/react-stripe-js';
import axios from 'axios';

const CheckoutForm = ({clientSecret}) => {
    const {cart, setPaymentRef, setOrderId} = useContext(AppContext);
    const stripe = useStripe();
    const elements = useElements();
    const navigate = useNavigate();

    const [message, setMessage] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!stripe || !elements) {
            return;
        }

        setIsLoading(true);

        const { error, paymentIntent } = await stripe.confirmPayment({
            elements,
            confirmParams: {
            },
            redirect: 'if_required',
        });

        if(!error) {
            const paymentId = paymentIntent.id;
            setPaymentRef(paymentId);
            const data = {
                products: cart,
                paymentRef: paymentId
            };
            axios.post("http://localhost:8080/api/payments/save-order", JSON.stringify(data), {
                headers: {"Content-Type": "application/json"},
            })
            .then((res) => {
                setOrderId(res.data.id);
                navigate('/success');
            })
                .catch((err) => {
                    setMessage("Server Error Occurred.");
                })
        }
        else
            if (error.type === "card_error" || error.type === "validation_error") {
                setMessage(error.message);
            } else {
                setMessage("An unexpected error occurred.");
            }

        setIsLoading(false);
    };

    const paymentElementOptions = {
        layout: "tabs"
    }
    return (
        <form className={styles.paymentForm}  onSubmit={handleSubmit} >
            <AddressElement options={{mode: 'shipping'}} />
            <PaymentElement options={paymentElementOptions} />
            <button type="submit" disabled={isLoading || !stripe || !elements} id="submit" className={styles.submitButton}>
                {isLoading ? <div className={styles.spinner} id="spinner"></div> : `Pay $${cart.reduce((price, product) => price + product.price, 0).toFixed(2)}`}
            </button>
            {message && <div id="payment-message">{message}</div>}
        </form>
    )
}

export default CheckoutForm;