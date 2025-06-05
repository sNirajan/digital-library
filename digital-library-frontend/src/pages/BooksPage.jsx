import { useEffect, useState } from 'react';
import axios from 'axios';

function BooksPage() {
  const [books, setBooks] = useState([]);

  const fetchBooks = () => {
    axios.get('http://localhost:8080/books')
      .then(res => {
        console.log("Books from backend:", res.data); // debug
        setBooks(res.data);
      })
      .catch(err => console.error("Error fetching books:", err));
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  const handleBorrow = (bookId) => {
    const userId = 1; // hardcoded until login is added

    axios.post('http://localhost:8080/borrowing', {
      userId,
      bookId
    })
    .then(res => {
      alert(`Book borrowed! Transaction ID: ${res.data.id}`);
      fetchBooks(); // refresh book list
    })
    .catch(err => {
      console.error("Borrowing failed:", err);
      alert("Failed to borrow the book.");
    });
  };

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-3xl font-bold mb-6 text-center text-blue-800">Library Books</h1>

      {books.length === 0 ? (
        <p className="text-center text-gray-500">No books found.</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
          {books.map(book => (
            <div key={book.id} className="bg-white p-4 rounded shadow-md hover:shadow-lg transition">
              <h2 className="text-xl font-semibold">{book.title}</h2>
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
      )}
    </div>
  );
}

export default BooksPage;
