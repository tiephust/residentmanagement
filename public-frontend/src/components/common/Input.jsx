import PropTypes from 'prop-types';
import './Input.css';

const Input = ({
                   type = 'text',
                   name,
                   value,
                   onChange,
                   placeholder = '',
                   label,
                   error,
                   required = false
               }) => {
    return (
        <div className="form-group">
            {label && <label htmlFor={name}>{label}{required && <span className="required">*</span>}</label>}
            <input
                type={type}
                id={name}
                name={name}
                value={value}
                onChange={onChange}
                placeholder={placeholder}
                className={`form-control ${error ? 'is-invalid' : ''}`}
            />
            {error && <div className="invalid-feedback">{error}</div>}
        </div>
    );
};

Input.propTypes = {
    type: PropTypes.string,
    name: PropTypes.string.isRequired,
    value: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    placeholder: PropTypes.string,
    label: PropTypes.string,
    error: PropTypes.string,
    required: PropTypes.bool
};

export default Input;