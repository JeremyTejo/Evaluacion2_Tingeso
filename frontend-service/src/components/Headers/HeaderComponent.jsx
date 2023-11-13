import React from 'react'
import { useNavigate } from 'react-router-dom';

function HeaderComponent() {
    const navigate = useNavigate();
    const handleClick = () => {
        navigate("/");
    }
    return (
        <div>
            <header className="header">
                <div className="logo">
                    <h1 onClick={handleClick}>Academia Preuniversitaria TopEducation 2023 </h1>
                </div>
                <nav></nav>
            </header>
        </div>
    )
}

export default HeaderComponent
