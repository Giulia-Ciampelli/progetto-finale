import { useContext, useEffect } from "react";

// context
import APIContext from "../context/APIContext.jsx";

// custom hooks
import usePageTitle from "../hooks/PageTitle.jsx";

export default function Home() {
    const { setTitle } = useContext(APIContext);
    usePageTitle("GamerHub - Home");

    useEffect(() => {
        setTitle("Home");
    })

    return (
        <>
            <div className="col">
                <h2 className="my-4">
                    🎮 Welcome to GamerHub
                </h2>
                <h4 className="my-4">
                    🕹️ Discover Games
                    Browse our curated collection of video games available across all major platforms. Whether you're into action-packed adventures or relaxing indie gems, we've got something for you.
                </h4>
                <p className="fs-5 my-4">
                    🧩 By Platform<br />
                    Explore games on:<br />
                    - PC<br />
                    - PlayStation<br />
                    - Xbox<br />
                    - Nintendo Switch<br />
                    - Mobile<br />
                </p>
                <p className="fs-5 my-4">
                    🏷️ By Tags<br />
                    Find games with popular themes and genres:<br />
                    Strategy, Horror, Casual, Multiplayer, Open World, Puzzle, and more...
                </p>
                <p className="fs-5 my-4">
                    💸 Current Sales<br />
                    Don't miss out on special offers! Look out for:<br />
                    - Discounted prices<br />
                    - Limited-time deals<br />
                    - Highlighted sales on top-rated games<br />
                </p>
            </div>
        </>
    )
}