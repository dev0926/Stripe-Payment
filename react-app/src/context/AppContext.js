import { createContext, useState, useMemo } from 'react';

export const AppContext = createContext(null);

export const AppContextProvider = ({children}) => {
    const [cart, setCart] = useState([]);
    const [paymentRef, setPaymentRef] = useState("");
    const [orderId, setOrderId] = useState(null);

    const value = useMemo(() => ({
        cart,
        setCart,
        paymentRef,
        setPaymentRef,
        orderId,
        setOrderId
    }), [
        cart,
        setCart,
        paymentRef,
        setPaymentRef,
        orderId,
        setOrderId
    ]);

    return (
        <AppContext.Provider value={value} >
            {children}
        </AppContext.Provider>
    )
}