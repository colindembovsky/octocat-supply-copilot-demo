import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { useTheme } from '../context/ThemeContext';

export default function CreateAccount() {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    password: '',
    confirmPassword: ''
  });
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();
  const { register } = useAuth();
  const { darkMode } = useTheme();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const validateForm = () => {
    if (!formData.firstName.trim()) {
      setError('First name is required');
      return false;
    }
    if (!formData.lastName.trim()) {
      setError('Last name is required');
      return false;
    }
    if (!formData.email.trim()) {
      setError('Email is required');
      return false;
    }
    if (formData.password.length < 6) {
      setError('Password must be at least 6 characters long');
      return false;
    }
    if (formData.password !== formData.confirmPassword) {
      setError('Passwords do not match');
      return false;
    }
    return true;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    if (!validateForm()) {
      return;
    }

    setIsLoading(true);
    try {
      await register(formData.email, formData.password, formData.firstName, formData.lastName);
      navigate('/login?success=Account created successfully! Please login.');
    } catch (err: any) {
      setError(err.message || 'Account creation failed. Please try again.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className={`min-h-screen pt-20 ${darkMode ? 'bg-dark' : 'bg-gray-100'} flex items-center justify-center px-4 transition-colors duration-300`}>
      <div className={`max-w-md w-full ${darkMode ? 'bg-gray-800' : 'bg-white'} rounded-lg shadow-lg p-8 transition-colors duration-300`}>
        <h2 className={`text-3xl font-bold ${darkMode ? 'text-light' : 'text-gray-800'} mb-6 transition-colors duration-300`}>Create Account</h2>
        
        {error && (
          <div className="bg-red-500/10 border border-red-500 text-red-500 rounded-md p-3 mb-4">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label htmlFor="firstName" className={`block ${darkMode ? 'text-light' : 'text-gray-700'} mb-2 transition-colors duration-300`}>
                First Name
              </label>
              <input
                id="firstName"
                name="firstName"
                type="text"
                value={formData.firstName}
                onChange={handleChange}
                className={`w-full ${darkMode ? 'bg-gray-700 text-light' : 'bg-gray-100 text-gray-800'} rounded px-3 py-2 transition-colors duration-300`}
                required
                autoFocus
              />
            </div>

            <div>
              <label htmlFor="lastName" className={`block ${darkMode ? 'text-light' : 'text-gray-700'} mb-2 transition-colors duration-300`}>
                Last Name
              </label>
              <input
                id="lastName"
                name="lastName"
                type="text"
                value={formData.lastName}
                onChange={handleChange}
                className={`w-full ${darkMode ? 'bg-gray-700 text-light' : 'bg-gray-100 text-gray-800'} rounded px-3 py-2 transition-colors duration-300`}
                required
              />
            </div>
          </div>

          <div>
            <label htmlFor="email" className={`block ${darkMode ? 'text-light' : 'text-gray-700'} mb-2 transition-colors duration-300`}>
              Email Address
            </label>
            <input
              id="email"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
              className={`w-full ${darkMode ? 'bg-gray-700 text-light' : 'bg-gray-100 text-gray-800'} rounded px-3 py-2 transition-colors duration-300`}
              required
            />
          </div>

          <div>
            <label htmlFor="password" className={`block ${darkMode ? 'text-light' : 'text-gray-700'} mb-2 transition-colors duration-300`}>
              Password
            </label>
            <input
              id="password"
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
              className={`w-full ${darkMode ? 'bg-gray-700 text-light' : 'bg-gray-100 text-gray-800'} rounded px-3 py-2 transition-colors duration-300`}
              required
              minLength={6}
            />
            <p className={`text-sm ${darkMode ? 'text-gray-400' : 'text-gray-600'} mt-1`}>
              Must be at least 6 characters long
            </p>
          </div>

          <div>
            <label htmlFor="confirmPassword" className={`block ${darkMode ? 'text-light' : 'text-gray-700'} mb-2 transition-colors duration-300`}>
              Confirm Password
            </label>
            <input
              id="confirmPassword"
              name="confirmPassword"
              type="password"
              value={formData.confirmPassword}
              onChange={handleChange}
              className={`w-full ${darkMode ? 'bg-gray-700 text-light' : 'bg-gray-100 text-gray-800'} rounded px-3 py-2 transition-colors duration-300`}
              required
            />
          </div>

          <div className="space-y-3 pt-4">
            <button
              type="submit"
              disabled={isLoading}
              className="w-full bg-primary hover:bg-accent text-white py-2 px-4 rounded transition-colors disabled:opacity-50"
            >
              {isLoading ? 'Creating Account...' : 'Create Account'}
            </button>
            
            <Link
              to="/login"
              className={`w-full ${darkMode ? 'bg-gray-700 hover:bg-gray-600 text-light' : 'bg-gray-200 hover:bg-gray-300 text-gray-800'} py-2 px-4 rounded transition-colors block text-center`}
            >
              Back to Login
            </Link>
          </div>
        </form>

        <div className={`mt-6 text-center ${darkMode ? 'text-gray-400' : 'text-gray-600'}`}>
          Already have an account?{' '}
          <Link to="/login" className="text-primary hover:text-accent transition-colors">
            Login here
          </Link>
        </div>
      </div>
    </div>
  );
}
