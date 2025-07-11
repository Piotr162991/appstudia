import classNames from 'classnames'
import React from 'react'
import './form.scss'

const Form = ({children, onSubmit, className}) => {
    return (
        <form onSubmit={onSubmit} className={classNames("form", className)}>
            {children}
        </form>
    )
}

export default Form