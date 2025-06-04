import { useEffect, useState } from 'react';
import axios from 'axios';

function BooksPage() {
  const [books, setBooks] = useState([]);

  // Fetch books from backend
  const fetchBooks = () => {
    axios.get('http://localhost:8080/books')
      .then(res => setBooks(res.data))
      .catch(err => console.error("Error fetching books:", err));
  };

  // Call once on mount
  useEffect(() => {
  axios.get('http://localhost:8080/books')
    .then(res => {
      console.log("Books fetched:", res.data); // 👈 this line
      setBooks(res.data);
    })
    .catch(err => console.error("Error fetching books:", err));
}, []);

  // Handle borrowing a book
  const handleBorrow = (bookId) => {
    const userId = 1; // 👤 Hardcoded user ID (to be replaced with real login later)

    axios.post('http://localhost:8080/borrowings', {
      userId,
      bookId
    })
    .then(res => {
      alert(`Book borrowed! Transaction ID: ${res.data.id}`);
      fetchBooks(); // Refresh the list after borrowing
    })
    .catch(err => {
      console.error("Borrowing failed:", err);
      alert("Failed to borrow the book.");
    });
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-3xl font-bold mb-6 text-center text-blue-800">Library Books</h1>

      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
        {books.map(book => (
          <div key={book.id} className="bg-white p-4 rounded shadow-md hover:shadow-lg transition">
            <h2 className="text-xl font-semibold text-gray-800">{book.title}</h2>
            <p className="text-sm text-gray-600">Author: {book.author}</p>
            <p className="text-sm text-gray-600">Genre: {book.genre}</p>
            <p className="text-sm text-gray-600">Available: {book.available ? "Yes" : "No"}</p>

            <button
              className={`mt-4 w-full px-4 py-2 text-white font-medium rounded ${
                book.available ? 'bg-green-600 hover:bg-green-700' : 'bg-gray-400 cursor-not-allowed'
              }`}
              disabled={!book.available}
              onClick={() => handleBorrow(book.id)}
            >
              {book.available ? "Borrow" : "Not Available"}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default BooksPage;
