import React, { useState, useEffect } from 'react';
import './Employer.scss';
import * as actions from './EmployerActions';
import Table from "../../elements/layouts/table/Table";

const Employer = () => {
    const [employers, setEmployers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [searchTerm, setSearchTerm] = useState('');
    const [industryFilter, setIndustryFilter] = useState('');

    useEffect(() => {
        actions.loadEmployer((data) => {
            setEmployers(data);
            setLoading(false);
        });
    }, []);

    const columns = React.useMemo(
        () => [
            {
                Header: 'Nazwa firmy',
                accessor: 'companyName',
                cellClass: 'company-name-cell'
            },
            {
                Header: 'Branża',
                accessor: 'industry',
                cellClass: 'industry-cell',
                Cell: ({ value }) => (
                    <span className={`industry-badge ${value.toLowerCase()}`}>
                        {value}
                    </span>
                )
            },
            {
                Header: 'Lokalizacja',
                accessor: 'city',
                cellClass: 'location-cell',
                Cell: ({ row }) => (
                    <div className="location-info">
                        <span className="city">{row.original.city}</span>
                        <span className="postal-code">{row.original.postalCode}</span>
                    </div>
                )
            },
            {
                Header: 'Kontakt',
                accessor: 'email',
                cellClass: 'contact-cell',
                Cell: ({ row }) => (
                    <div className="contact-info">
                        <div className="email">{row.original.email}</div>
                        <div className="phone">{row.original.phone}</div>
                    </div>
                )
            },
            {
                Header: 'Zatrudnionych',
                accessor: 'numberOfEmployees',
                cellClass: 'employees-cell',
                Cell: ({ value }) => (
                    <div className="employees-badge">
                        {value}
                    </div>
                )
            },
            {
                Header: 'Rok założenia',
                accessor: 'foundedYear',
                cellClass: 'founded-cell'
            },
        ],
        []
    );

    const handleRowClick = (row) => {
        console.log('Wybrano pracodawcę:', row.original);
        // Tutaj możesz dodać nawigację do szczegółów pracodawcy
        // np. history.push(`/employers/${row.original.id}`);
    };

    const filteredData = employers.filter(employer => {
        const matchesSearch = employer.companyName.toLowerCase().includes(searchTerm.toLowerCase()) ||
            employer.industry.toLowerCase().includes(searchTerm.toLowerCase()) ||
            employer.city.toLowerCase().includes(searchTerm.toLowerCase());

        const matchesIndustry = industryFilter === '' || employer.industry === industryFilter;

        return matchesSearch && matchesIndustry;
    });

    if (loading) {
        return (
            <div className="loading-container">
                <div className="loading-spinner"></div>
                <div className="loading-text">Ładowanie pracodawców...</div>
            </div>
        );
    }

    return (
        <div className="employer-page">
            <div className="page-header">
                <div className="header-content">
                    <h1>Pracodawcy</h1>
                    <p className="subtitle">Zarządzaj bazą pracodawców w systemie</p>
                </div>
            </div>

            <div className="employer-dashboard">
                <div className="dashboard-card total-employers">
                    <div className="card-icon">
                        <i className="fas fa-building"></i>
                    </div>
                    <div className="card-content">
                        <h3>{employers.length}</h3>
                        <p>Pracodawców</p>
                    </div>
                </div>

                <div className="dashboard-card avg-employees">
                    <div className="card-icon">
                        <i className="fas fa-users"></i>
                    </div>
                    <div className="card-content">
                        <h3>
                            {Math.round(
                                employers.reduce((sum, emp) => sum + emp.numberOfEmployees, 0) / employers.length
                            )}
                        </h3>
                        <p>Średnia zatrudnionych</p>
                    </div>
                </div>

                <div className="dashboard-card top-industry">
                    <div className="card-icon">
                        <i className="fas fa-chart-pie"></i>
                    </div>
                    <div className="card-content">
                        <h3>Technologia</h3>
                        <p>Najczęstsza branża</p>
                    </div>
                </div>
            </div>

            <div className="employer-content">
                <div className="employer-filters">
                    <div className="search-container">
                        <i className="fas fa-search search-icon"></i>
                        <input
                            type="text"
                            placeholder="Szukaj pracodawców..."
                            className="search-input"
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                        />
                    </div>

                    <div className="filter-container">
                        <select
                            className="filter-select"
                            value={industryFilter}
                            onChange={(e) => setIndustryFilter(e.target.value)}
                        >
                            <option value="">Wszystkie branże</option>
                            <option value="Technology">Technologia</option>
                            <option value="Finance">Finanse</option>
                            <option value="Healthcare">Opieka zdrowotna</option>
                            <option value="Education">Edukacja</option>
                            <option value="Manufacturing">Produkcja</option>
                            <option value="Retail">Handel detaliczny</option>
                        </select>
                    </div>
                </div>

                <div className="table-container">
                    <Table
                        columns={columns}
                        data={filteredData}
                        className="employers-table"
                        additionalRowProps={(row) => ({
                            onClick: () => handleRowClick(row),
                            className: 'employer-row'
                        })}
                    />
                </div>

                <div className="table-footer">
                    <div className="showing-info">
                        Pokazuje {filteredData.length} z {employers.length} pracodawców
                    </div>

                    <div className="pagination">
                        <button className="pagination-btn" disabled>
                            <i className="fas fa-chevron-left"></i>
                        </button>
                        <button className="pagination-btn active">1</button>
                        <button className="pagination-btn">2</button>
                        <button className="pagination-btn">3</button>
                        <button className="pagination-btn">
                            <i className="fas fa-chevron-right"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Employer;