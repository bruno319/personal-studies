use std::{io, fs};
use std::io::Read;
use std::fs::File;

fn main() {
    read_username_from_file().unwrap();
    read_username_from_file_shorter().unwrap();
    read_username_from_file_even_shorter().unwrap();
}

fn read_username_from_file() -> Result<String, io::Error> {
    let f = File::open("hello.txt");

    let mut f = match f {
        Ok(file) => file,
        Err(e) => return Err(e),
    };

    let mut s = String::new();

    match f.read_to_string(&mut s) {
        Ok(_) => Ok(s),
        Err(e) => Err(e),
    }
}

fn read_username_from_file_shorter() -> Result<String, io::Error> {
    let mut f = File::open("hello.txt")?;
    let mut s = String::new();
    f.read_to_string(&mut s)?;
    Ok(s)
}

fn read_username_from_file_even_shorter() -> Result<String, io::Error> {
    fs::read_to_string("hello.txt")
}