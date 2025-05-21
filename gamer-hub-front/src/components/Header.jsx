import Navbar from "../components/Navbar.jsx";
import { useContext, useState } from "react";
import APIContext from "../context/APIContext.jsx";

export default function Header() {
    const { title } = useContext(APIContext);

    return (
        <header className="bg-card">
            <h1 className="mb-3 py-2 px-5">
                {title}
            </h1>
            <Navbar />
        </header>
    )
}