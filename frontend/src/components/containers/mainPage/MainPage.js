import React from 'react';
import './MainPage.scss';
import placeholderImage from '../../../resorces/images/placeholderjpeg.jpeg';


const MainPage = () => {
    return (
        <div className="main-page">
            <header className="main-header">
                <h1>WorkForce Pro</h1>
                <h2>Twój zaufany partner w znalezieniu wymarzonej pracy</h2>
            </header>

            <section className="hero-section">
                <div className="hero-content">
                    <h2>Znajdź swoją wymarzoną pracę już dziś!</h2>
                    <p>Jesteśmy agencją, która łączy najlepszych pracowników z najlepszymi pracodawcami.</p>
                    <button className="cta-button">Szukaj ofert</button>
                </div>
                <div className="hero-image">
                    <img
                        src={placeholderImage}
                        alt="Agencja pracy - rekrutacja"
                        className="main-image"
                    />
                </div>
            </section>

            <section className="features-section">
                <div className="feature">
                    <div className="feature-icon">🔍</div>
                    <h3>Szeroki wybór ofert</h3>
                    <p>Tysiące ofert pracy dostępnych na wyciągnięcie ręki</p>
                </div>
                <div className="feature">
                    <div className="feature-icon">🤝</div>
                    <h3>Profesjonalne doradztwo</h3>
                    <p>Eksperci, którzy pomogą Ci w każdym kroku</p>
                </div>
                <div className="feature">
                    <div className="feature-icon">📈</div>
                    <h3>Rozwój kariery</h3>
                    <p>Wspieramy Twój rozwój zawodowy i osobisty</p>
                </div>
            </section>

            <section className="testimonials-section">
                <h2>Co mówią nasi klienci</h2>
                <div className="testimonials">
                    <div className="testimonial">
                        <p>"Dzięki WorkForce Pro znalazłem pracę moich marzeń w zaledwie dwa tygodnie!"</p>
                        <p className="testimonial-author">- Jan Kowalski</p>
                    </div>
                    <div className="testimonial">
                        <p>"Profesjonalne podejście i indywidualne traktowanie każdego klienta - polecam!"</p>
                        <p className="testimonial-author">- Anna Nowak</p>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default MainPage;