import Navbar from "../components/Navbar.jsx";
import { useContext, useState } from "react";
import APIContext from "../context/APIContext.jsx";

export default function Header() {
    const { title } = useContext(APIContext);

    return (
        <header>
            <h1>
                {title}
            </h1>
            <Navbar />
        </header>
    )
}