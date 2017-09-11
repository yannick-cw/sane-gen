## Sane-Gen

Sane-gen is a library trying to make it easy to generate common data with `scalachek` generators.

### Currently supported

- [x] Names
- [x] Emails 
- [x] URLs
- [ ] english texts
- [ ] german texts
- [ ] passwords
- [ ] usernames

### Additional helper functions

- [ ] mix in own terms
- [ ] mix in own terms with given frequency

### Usage

You find all supported generators in `import io.github.yannick_cw.SaneGen`.

```scala
import io.github.yannick_cw.SaneGen


val maybeMail = SaneGen.email.sample
// Some(Email(Laverne#@aol.it))
```