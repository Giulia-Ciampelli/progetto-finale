import { createContext, useState, useEffect } from "react";
const APIContext = createContext();

export const APIContextProvider = ({ children }) => {
    const [games, setGames] = useState([]); // variabile index
    const [gameDetails, setGameDetails] = useState(null); // variabile show
    const url = "http://127.0.0.1:8080/api/games";
    const [loading, setLoading] = useState(false); // variabile loader

    // fetch index
    useEffect(() => {
        setLoading(true);

        fetch(url)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setGames(data);
                setLoading(false);
            })
            .catch(err => {
                console.log(err);
                setLoading(false);
            })
    }, [])

    // fetch show
    const fetchGameById = (id) => {
        setLoading(true);

        fetch(`${url}/${id}`)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setGameDetails(data);
                setLoading(false);
            })
            .catch(err => {
                console.log(err);
                setLoading(false);
            })
    }

    // ritorno provider
    return (
        <APIContext.Provider value={{ games, gameDetails, fetchGameById, loading }}>
            {children}
        </APIContext.Provider>
    )
}

export default APIContext;