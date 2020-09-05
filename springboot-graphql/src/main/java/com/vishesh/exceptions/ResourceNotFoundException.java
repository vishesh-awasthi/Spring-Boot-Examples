package com.vishesh.exceptions;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String s, long id) {
    super(String.format("%s%s", s, id));
  }
}
